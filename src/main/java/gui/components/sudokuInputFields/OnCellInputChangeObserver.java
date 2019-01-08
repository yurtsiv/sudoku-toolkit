package gui.components.sudokuInputFields;

public interface OnCellInputChangeObserver {
   void onChange(int row, int column, int value);
}
