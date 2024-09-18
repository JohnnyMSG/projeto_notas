import {Component, NgModule, ViewChild} from '@angular/core';
import {Nota} from "../../shared/models/nota";
import {NotaService} from "../../shared/services/notas/nota.service";
import {
  DxButtonModule,
  DxDataGridComponent,
  DxDataGridModule,
  DxPopupModule,
  DxSpeedDialActionModule
} from "devextreme-angular";
import {DxTextBoxTypes} from "devextreme-angular/ui/text-box";
import {ItensNotaService} from "../../shared/services/itensNota/itens-nota.service";
import {ItensNota} from "../../shared/models/itensNota";
import DevExpress from "devextreme";
import data = DevExpress.data;

type EditorOptions = DxTextBoxTypes.Properties;

@Component({
  selector: 'app-notas',
  templateUrl: './notas.component.html',
  styleUrl: './notas.component.scss'
})
export class NotasComponent {

  @ViewChild("dataGrip", {static: false}) dataGrid!: DxDataGridComponent;

  notas: Nota[] = [];
  notaCriada: Nota = new Nota();
  itensNota: ItensNota[] = [];
  value: Nota = new Nota();
  popupDeleteVisivel: boolean = false;
  apenasDigitosPattern = /^[\d.,]+$/;

  constructor(
    private serviceNotas: NotaService,
    private serviceItensNota: ItensNotaService
  ) {}

  ngOnInit() {
    this.carregarNotas();
    this.carregarItensNota();
  }

  carregarNotas() {
    this.serviceNotas.getNotas().subscribe(dadosNotas => {
      this.notas = dadosNotas;
    })
  }

  carregarItensNota() {
    this.serviceItensNota.getItensNota().subscribe(dadosItensNotas => {
      this.itensNota = dadosItensNotas;
    })
  }

  deletarNota(nota: Nota) {
    this.serviceNotas.deleteNota(nota.id).subscribe(() => {
      console.log("Nota deletada com sucesso!")
    })
  }

  deletarItemNota(e: any) {
    e.cancel = true;
    this.serviceItensNota.deleteItemNota(e.data.id).subscribe(() => {
      console.log("Item da Nota deletada com sucesso!")
      this.carregarNotas();
    })
  }

  onSavedNota(e: any) {
    for (let change of e.changes) {
      if (change.type == 'insert') {
        this.serviceNotas.postNota(change.data).subscribe(() => {
          console.log("Nota criado com sucesso!");
          this.carregarNotas();
        })
      } else if (change.type == 'update') {
        this.serviceNotas.updateNota(change.data.id, change.data).subscribe(() => {
          console.log("Nota atualizado com sucesso!")
        });
      }
    }
  }

  onSavedItemNota(e: any) {
    for (let change of e.changes) {
      if (change.type == 'insert') {
        this.serviceItensNota.postItemNota(change.data).subscribe(() => {
          console.log("Item da nota criado com sucesso!");
          this.carregarNotas();
        })
      } else if (change.type == 'update') {
        this.serviceItensNota.updateItemNota(change.data.id, change.data).subscribe(() => {
          console.log("Item da nota atualizado com sucesso!")
          this.carregarNotas();
        });
      }
    }
  }

  addRow() {
    this.dataGrid.instance.addRow();
  }

  popupConfirmacaoDelete(evento: any) {
    evento.cancel = true;
    this.value = evento.data
    this.popupDeleteVisivel = true;
  }

  onPressDeletar() {
    this.deletarNota(this.value);
    this.popupDeleteVisivel = false;
  }

  cancelarDelete() {
    this.popupDeleteVisivel = false
  }

  editorOptions: EditorOptions = {
    valueChangeEvent: 'keyup'
  };

}

@NgModule({
  declarations: [
    NotasComponent
  ],
  imports: [
    DxDataGridModule,
    DxSpeedDialActionModule,
    DxButtonModule,
    DxPopupModule,
  ]
})
export class NotasModule { }
