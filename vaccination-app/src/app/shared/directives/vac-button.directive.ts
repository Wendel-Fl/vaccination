import { Directive, ElementRef, Input, Renderer2 } from '@angular/core';

@Directive({
  selector: '[vacButton]'
})
export class VacButtonDirective {

  @Input()
  public vacButtonType: 'flat' | 'flat-ghost' | 'icon' = 'flat';

  @Input()
  public vacButtonIcon?: string = '';

  constructor(
    private renderer: Renderer2,
    private elementRef: ElementRef
  ) { 
    console.log(this.vacButtonType)
    this.renderer.setAttribute(this.elementRef.nativeElement, 'class', this.buildClasses().trim());
  }

  private buildClasses(): string {
    return `vac-button ${this.vacButtonType.split('-').join(' ')} ${this.vacButtonType === 'icon' ? this.vacButtonIcon : ''}`;
  }

}
