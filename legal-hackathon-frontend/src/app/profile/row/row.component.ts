import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-row',
  templateUrl: './row.component.html',
  styleUrls: ['./row.component.scss']
})
export class RowComponent {
  @Input() title: string;
  @Input() setting: boolean;
  @Input() imgSrc: string;
}
