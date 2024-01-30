import { Directive, ElementRef, Input, OnInit, Renderer2 } from '@angular/core';

@Directive({
  selector: '[vacButton]'
})
export class VacButtonDirective implements OnInit {

  @Input()
  public vacButton?: 'flat' | 'flat-ghost' | 'icon' = 'flat';

  @Input()
  public vacButtonIcon?: string = '';

  constructor(
    private renderer: Renderer2,
    private elementRef: ElementRef
  ) { 
  }

  ngOnInit(): void {
    this.renderer.setAttribute(this.elementRef.nativeElement, 'class', this.buildClasses().trim());
  }

  private buildClasses(): string {
    return `vac-button ${this.vacButton.split('-').join(' ')} ${this.vacButton === 'icon' ? this.vacButtonIcon : ''}`;
  }

}
