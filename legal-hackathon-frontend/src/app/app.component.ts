import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';

interface AnalysisResult {
  entities: Array<any>;
  geotrackingFulfilled: boolean;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  url: string;
  geotrackingWanted: boolean;
  isLoading: boolean;
  result: AnalysisResult;

  constructor(private http: HttpClient) {
  }

  checkForPrivacy() {
    this.isLoading = true;
    let requestURL = `http://localhost:8080/demo?url=${this.url}&geotracking=${!!this.geotrackingWanted}`;
    this.http.get(requestURL).subscribe((result: Array<any>) => {
      this.result = {
        entities: result,
        geotrackingFulfilled: result.some(entity => entity.geotrackingFulfilled)
      };

      this.isLoading = false;
    });
  }
}
