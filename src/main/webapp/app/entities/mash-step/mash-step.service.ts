import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMashStep } from 'app/shared/model/mash-step.model';

type EntityResponseType = HttpResponse<IMashStep>;
type EntityArrayResponseType = HttpResponse<IMashStep[]>;

@Injectable({ providedIn: 'root' })
export class MashStepService {
    public resourceUrl = SERVER_API_URL + 'api/mash-steps';

    constructor(protected http: HttpClient) {}

    create(mashStep: IMashStep): Observable<EntityResponseType> {
        return this.http.post<IMashStep>(this.resourceUrl, mashStep, { observe: 'response' });
    }

    update(mashStep: IMashStep): Observable<EntityResponseType> {
        return this.http.put<IMashStep>(this.resourceUrl, mashStep, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMashStep>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMashStep[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
