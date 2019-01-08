/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JbeerappTestModule } from '../../../test.module';
import { FermentableDetailComponent } from 'app/entities/fermentable/fermentable-detail.component';
import { Fermentable } from 'app/shared/model/fermentable.model';

describe('Component Tests', () => {
    describe('Fermentable Management Detail Component', () => {
        let comp: FermentableDetailComponent;
        let fixture: ComponentFixture<FermentableDetailComponent>;
        const route = ({ data: of({ fermentable: new Fermentable(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JbeerappTestModule],
                declarations: [FermentableDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FermentableDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FermentableDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.fermentable).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
