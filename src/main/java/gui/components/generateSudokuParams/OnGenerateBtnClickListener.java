package gui.components.generateSudokuParams;

import logic.sudoku.generator.SudokuDifficulty;

public interface OnGenerateBtnClickListener {
     void onClick(SudokuDifficulty difficulty, boolean showSolution);
}
