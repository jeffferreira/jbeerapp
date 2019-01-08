import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHop } from 'app/shared/model/hop.model';

type EntityResponseType = HttpResponse<IHop>;
type EntityArrayResponseType = HttpResponse<IHop[]>;

@Injectable({ providedIn: 'root' })
export class HopService {
    public resourceUrl = SERVER_API_URL + 'api/hops';

    constructor(protected http: HttpClient) {}

    create(hop: IHop): Observable<EntityResponseType> {
        return this.http.post<IHop>(this.resourceUrl, hop, { observe: 'response' });
    }

    update(hop: IHop): Observable<EntityResponseType> {
        return this.http.put<IHop>(this.resourceUrl, hop, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IHop>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IHop[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
