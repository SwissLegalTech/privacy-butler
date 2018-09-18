import {Component} from '@angular/core';
import {SettingsService} from '../settings.service';
import {HttpClient} from '@angular/common/http';
import {finalize} from 'rxjs/operators';

@Component({
  selector: 'app-browser',
  templateUrl: './browser.component.html',
  styleUrls: ['./browser.component.scss']
})
export class BrowserComponent {
  url: string;
  geotrackingFulfilled: boolean;
  datasharingFulfilled: boolean;
  profilingFulfilled: boolean;
  isLoading: boolean;
  result: AnalysisResult;
  percentage: number;

  constructor(private settings: SettingsService, private http: HttpClient) {
  }

  checkSite() {
    this.isLoading = true;
    const requestURL = `http://localhost:8080/demo?url=${this.url}`;
    this.http.get(requestURL)
      .pipe(finalize(() => this.isLoading = false))
      .subscribe((result: AnalysisResult) => {
        this.result = result;
        this.geotrackingFulfilled = this.isFulfilled(result.alowanceMap.geotracking, this.settings.geotrackingWanted);
        this.datasharingFulfilled = this.isFulfilled(result.alowanceMap.dritte, this.settings.dataSharingWanted);
        this.profilingFulfilled = this.isFulfilled(result.alowanceMap.profiling, this.settings.profilingWanted);
        this.calculatePercentage();
      });
  }

  private isFulfilled(backendValue: boolean, profileValue: boolean) {
    return !backendValue || backendValue === profileValue;
  }

  allFulfilled(): boolean {
    return this.geotrackingFulfilled && this.datasharingFulfilled && this.profilingFulfilled;
  }

  private calculatePercentage() {
    let count = 0;
    if (this.geotrackingFulfilled) {
      count++;
    }
    if (this.profilingFulfilled) {
      count++;
    }
    if (this.datasharingFulfilled) {
      count++;
    }
    this.percentage = Math.floor(count / 3 * 100);
  }
}

interface AllowanceMap {
  profiling?: boolean;
  dritte?: boolean;
  geotracking?: boolean;
}

interface AnalysisResult {
  valid: boolean;
  alowanceMap: AllowanceMap;
  foundEntities: Array<string>;
}
