import { AfterViewInit, Component, ElementRef, Input, ViewChild } from '@angular/core';

type Alignments = 'center' | 'space-between' | 'flex-start' | 'flex-end';

type Axis = 'column' | 'row';

@Component({
  selector: 'vac-flex',
  template: `
    <div #vacFlex class="vac-flex">
      <ng-content></ng-content>
    </div>
  `,
  styleUrl: './vac-flex.component.scss'
})
export class VacFlexComponent implements AfterViewInit {

  @ViewChild('vacFlex')
  private vacFlex: ElementRef<HTMLElement>;

  @Input()
  public mainAxis: Axis = 'row';

  @Input()
  public gap: string = '0';

  @Input()
  public justify: Alignments = 'flex-start';

  @Input()
  public align: Alignments = 'flex-start';

  ngAfterViewInit(): void {
    this.vacFlex.nativeElement.style.flexDirection = this.mainAxis;
    if (!Number.isNaN(this.gap))
      this.vacFlex.nativeElement.style.gap = `${this.gap}px`;
    this.vacFlex.nativeElement.style.justifyContent = this.justify;
    this.vacFlex.nativeElement.style.alignItems = this.align;
  }
  
}
