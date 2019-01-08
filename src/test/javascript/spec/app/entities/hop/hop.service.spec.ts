/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { HopService } from 'app/entities/hop/hop.service';
import { IHop, Hop } from 'app/shared/model/hop.model';

describe('Service Tests', () => {
    describe('Hop Service', () => {
        let injector: TestBed;
        let service: HopService;
        let httpMock: HttpTestingController;
        let elemDefault: IHop;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(HopService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Hop(0, 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Hop', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Hop(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Hop', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        origin: 'BBBBBB',
                        alpha: 1,
                        amount: 1,
                        use: 'BBBBBB',
                        time: 1,
                        displayTime: 'BBBBBB',
                        displayAmount: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Hop', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        origin: 'BBBBBB',
                        alpha: 1,
                        amount: 1,
                        use: 'BBBBBB',
                        time: 1,
                        displayTime: 'BBBBBB',
                        displayAmount: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Hop', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
