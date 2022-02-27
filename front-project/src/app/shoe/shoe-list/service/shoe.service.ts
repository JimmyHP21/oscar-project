import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError } from "rxjs";
import { retry, catchError } from "rxjs/operators";
import { environment } from "src/environments/environment";

@Injectable({ providedIn: "root" })
export class ShoeService {
  url = '/v1/shoe/'

  headers = {
    'Expires': '0',
    'Pragma': 'no-cache',
    'Cache-Control': 'no-cache, no-store, must-revalidate',
    'Access-Control-Allow-Headers': 'Access-Control-Allow-Origin, Content-Type, Accept, Accept-Language, Origin, User-Agent',
  };

  /**
   * Constructor
   *
   * @param {HttpClient} _httpClient
   */
   constructor(private _httpClient: HttpClient) { }

   /**
   * Get all show
   *
   * @returns {Promise<any>}
   */
  getListShoe(): Observable<any> {
    return this._httpClient.get(environment.urlHost + this.url + 'list', { headers: this.headers })
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  deleteShoe(id): Promise<any> {
    return new Promise((resolve, reject) => {
      this._httpClient.delete(environment.urlHost + this.url + 'delete/' + id, { headers: this.headers })
        .subscribe((response: any) => {
          resolve(response);
        }, reject);
    })
  }

   // Manipulação de erros
   handleError(error: HttpErrorResponse): any {
    let errorMessage: string;
    if (error.error instanceof ErrorEvent) {
      // Erro ocorreu no lado do client
      errorMessage = error.error.message;
    } else {
      // Erro ocorreu no lado do servidor
      errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }
    return throwError(errorMessage);
  }
}
