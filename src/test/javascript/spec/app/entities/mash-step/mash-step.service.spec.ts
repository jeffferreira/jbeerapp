/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { MashStepService } from 'app/entities/mash-step/mash-step.service';
import { IMashStep, MashStep } from 'app/shared/model/mash-step.model';

describe('Service Tests', () => {
    describe('MashStep Service', () => {
        let injector: TestBed;
        let service: MashStepService;
        let httpMock: HttpTestingController;
        let elemDefault: IMashStep;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(MashStepService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new MashStep(0, 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
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

            it('should create a MashStep', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new MashStep(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a MashStep', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        type: 'BBBBBB',
                        stepTime: 1,
                        stepTemp: 1,
                        description: 'BBBBBB',
                        infuseTemp: 'BBBBBB',
                        displayStepTemp: 'BBBBBB',
                        displayInfuseAmt: 'BBBBBB'
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

            it('should return a list of MashStep', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        type: 'BBBBBB',
                        stepTime: 1,
                        stepTemp: 1,
                        description: 'BBBBBB',
                        infuseTemp: 'BBBBBB',
                        displayStepTemp: 'BBBBBB',
                        displayInfuseAmt: 'BBBBBB'
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

            it('should delete a MashStep', async () => {
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
