import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFermentable } from 'app/shared/model/fermentable.model';

type EntityResponseType = HttpResponse<IFermentable>;
type EntityArrayResponseType = HttpResponse<IFermentable[]>;

@Injectable({ providedIn: 'root' })
export class FermentableService {
    public resourceUrl = SERVER_API_URL + 'api/fermentables';

    constructor(protected http: HttpClient) {}

    create(fermentable: IFermentable): Observable<EntityResponseType> {
        return this.http.post<IFermentable>(this.resourceUrl, fermentable, { observe: 'response' });
    }

    update(fermentable: IFermentable): Observable<EntityResponseType> {
        return this.http.put<IFermentable>(this.resourceUrl, fermentable, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFermentable>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFermentable[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
