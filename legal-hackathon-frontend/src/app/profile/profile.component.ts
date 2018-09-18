import {Component} from '@angular/core';
import {SettingsService} from '../settings.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent {
  constructor(private settings: SettingsService) {
  }

  private _geo = false;

  get geo(): boolean {
    return this._geo;
  }

  set geo(value: boolean) {
    this._geo = value;
    this.settings.geotrackingWanted = value;
  }

  private _profiling = false;

  get profiling(): boolean {
    return this._profiling;
  }

  set profiling(value: boolean) {
    this._profiling = value;
    this.settings.profilingWanted = value;
  }

  private _dataSharing = false;

  get dataSharing(): boolean {
    return this._dataSharing;
  }

  set dataSharing(value: boolean) {
    this._dataSharing = value;
    this.settings.dataSharingWanted = value;
  }
}
