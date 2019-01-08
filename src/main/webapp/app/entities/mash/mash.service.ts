import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMash } from 'app/shared/model/mash.model';

type EntityResponseType = HttpResponse<IMash>;
type EntityArrayResponseType = HttpResponse<IMash[]>;

@Injectable({ providedIn: 'root' })
export class MashService {
    public resourceUrl = SERVER_API_URL + 'api/mashes';

    constructor(protected http: HttpClient) {}

    create(mash: IMash): Observable<EntityResponseType> {
        return this.http.post<IMash>(this.resourceUrl, mash, { observe: 'response' });
    }

    update(mash: IMash): Observable<EntityResponseType> {
        return this.http.put<IMash>(this.resourceUrl, mash, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMash>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMash[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
