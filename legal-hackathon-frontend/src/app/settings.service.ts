import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SettingsService {
  geotrackingWanted = false;
  profilingWanted = false;
  dataSharingWanted = false;
}
