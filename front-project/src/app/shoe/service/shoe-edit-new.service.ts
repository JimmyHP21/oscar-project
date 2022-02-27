import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";

@Injectable({ providedIn: "root" })
export class ShoeEditNewService {
  url = environment.urlHost + '/v1/shoe/';

  headers = {
    'Expires': '0',
    'Pragma': 'no-cache',
    'Cache-Control': 'no-cache, no-store, must-revalidate',
    'Access-Control-Allow-Headers': 'Access-Control-Allow-Origin, Content-Type, Accept, Accept-Language, Origin, User-Agent'
  };

  /**
   * Constructor
   *
   * @param {HttpClient} _httpClient
   */
   constructor(private _httpClient: HttpClient) { }

   /**
   * Save shoe
   *
   * @param profile
   * @returns {Promise<any>}
   */
  saveShoe(shoe): Promise<any> {
    return new Promise((resolve, reject) => {
      this._httpClient.put(this.url + 'update', shoe, { headers: this.headers })
        .subscribe((response: any) => {
          resolve(response);
        }, reject);
    });
  }

  /**
   * Add shoe
   *
   * @param profile
   * @returns {Promise<any>}
   */
  addShoe(shoe): Promise<any> {
    return new Promise((resolve, reject) => {
      this._httpClient.post(this.url + 'new', shoe, { headers: this.headers })
        .subscribe((response: any) => {
          resolve(response);
        }, reject);
    });
  }
}
