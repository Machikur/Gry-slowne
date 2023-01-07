import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { TableComponent } from './table/table.component';
import { ScrabbleCellComponent } from './scrabble-cell/scrabble-cell.component';
import { ScrabbleBlockComponent } from './scrabble-block/scrabble-block.component';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { PlayerMenuComponent } from './player-menu/player-menu.component';
import { GameData } from './data/game-data';
import { TableInfoComponent } from './table-info/table-info.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TableComponent,
    ScrabbleCellComponent,
    ScrabbleBlockComponent,
    PlayerMenuComponent,
    TableInfoComponent,
  ],
  imports: [BrowserModule, HttpClientModule, DragDropModule],
  providers: [GameData],
  bootstrap: [AppComponent],
})
export class AppModule {}
