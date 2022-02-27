import { ShoeService } from './service/shoe.service';
import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Shoe } from './model/shoe.model';
import { MatSort } from '@angular/material/sort';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-shoe-list',
  templateUrl: './shoe-list.component.html',
  styleUrls: ['./shoe-list.component.scss']
})
export class ShoeListComponent implements OnInit {

  @Input() listReceiver;
  @Output() newEdit = new EventEmitter();
  @Output() shoeDelete = new EventEmitter();

  displayedColumns: string[] = ['id', 'size', 'color', 'brand', 'price', 'quantity', 'edit'];
  dataSource = new MatTableDataSource<Shoe>();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true })
  sort: MatSort;

  confirmDialogRef: MatDialogRef<ConfirmDialogComponent>;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  constructor(public _matDialog: MatDialog){ }

  ngOnInit(): void {
    this.listShoe();
  }

  listShoe() {
    this.dataSource = new MatTableDataSource(this.listReceiver);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  deleteShoe(id) {
    this.confirmDialogRef = this._matDialog.open(ConfirmDialogComponent, {
      disableClose: false
    });

    this.confirmDialogRef.componentInstance.confirmMessage = 'Voce tem certeza que quer excluir ?';

    this.confirmDialogRef.afterClosed().subscribe(result => {

      if (result) {
        this.shoeDelete.emit(id);
      }

      this.confirmDialogRef = null;
    });
  }

  editShoe(element) {
    this.newEdit.emit({page: 'edit', obj: element});
  }
}
