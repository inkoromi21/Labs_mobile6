package com.example.unscramble.ui

import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.getUnscrambledWord
import org.junit.Assert.*
import org.junit.Test

class GameViewModelTest {
  private val viewModel = GameViewModel()

  private fun answerCurrentWord() {
    val word = getUnscrambledWord(viewModel.uiState.value.currentScrambledWord)
    viewModel.updateUserGuess(word)
    viewModel.checkUserGuess()
  }

  @Test
  fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset() {
    viewModel.updateUserGuess("wrong")
    viewModel.checkUserGuess()
    answerCurrentWord()
    val state = viewModel.uiState.value
    assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, state.score)
    assertEquals(2, state.currentWordCount)
    assertFalse(state.isGuessedWordWrong)
    assertEquals("", viewModel.userGuess)
  }

  @Test
  fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
    val initial = viewModel.uiState.value
    viewModel.updateUserGuess("wrong")
    viewModel.checkUserGuess()
    val state = viewModel.uiState.value
    assertTrue(state.isGuessedWordWrong)
    assertEquals(initial.score, state.score)
    assertEquals(initial.currentWordCount, state.currentWordCount)
    assertEquals(initial.currentScrambledWord, state.currentScrambledWord)
    assertEquals("", viewModel.userGuess)
  }

  @Test
  fun gameViewModel_Initialization_FirstWordLoaded() {
    val state = viewModel.uiState.value
    val original = getUnscrambledWord(state.currentScrambledWord)
    assertTrue(state.currentScrambledWord.isNotEmpty())
    assertNotEquals(original, state.currentScrambledWord)
    assertEquals(original.toList().sorted(), state.currentScrambledWord.toList().sorted())
    assertEquals(1, state.currentWordCount)
    assertEquals(0, state.score)
    assertFalse(state.isGameOver)
    assertFalse(state.isGuessedWordWrong)
  }

  @Test
  fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
    val words = mutableSetOf<String>()
    repeat(MAX_NO_OF_WORDS) { index ->
      val current = viewModel.uiState.value
      assertEquals(index + 1, current.currentWordCount)
      assertFalse(current.isGameOver)
      assertTrue(words.add(getUnscrambledWord(current.currentScrambledWord)))
      answerCurrentWord()
    }
    val state = viewModel.uiState.value
    assertTrue(state.isGameOver)
    assertEquals(MAX_NO_OF_WORDS, state.currentWordCount)
    assertEquals(MAX_NO_OF_WORDS * SCORE_INCREASE, state.score)
    assertFalse(state.isGuessedWordWrong)
  }

  @Test
  fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased() {
    answerCurrentWord()
    val initial = viewModel.uiState.value
    viewModel.updateUserGuess("wrong")
    viewModel.checkUserGuess()
    viewModel.skipWord()
    val state = viewModel.uiState.value
    assertEquals(initial.score, state.score)
    assertEquals(initial.currentWordCount + 1, state.currentWordCount)
    assertNotEquals(
      getUnscrambledWord(initial.currentScrambledWord),
      getUnscrambledWord(state.currentScrambledWord)
    )
    assertFalse(state.isGuessedWordWrong)
    assertEquals("", viewModel.userGuess)
  }

  @Test
  fun gameViewModel_ResetGame_InitialStateRestored() {
    answerCurrentWord()
    repeat(MAX_NO_OF_WORDS - 1) { viewModel.skipWord() }
    assertTrue(viewModel.uiState.value.isGameOver)
    assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, viewModel.uiState.value.score)
    viewModel.updateUserGuess("test")
    viewModel.resetGame()
    val state = viewModel.uiState.value
    assertFalse(state.isGameOver)
    assertFalse(state.isGuessedWordWrong)
    assertEquals(0, state.score)
    assertEquals(1, state.currentWordCount)
    assertTrue(state.currentScrambledWord.isNotEmpty())
    assertEquals("", viewModel.userGuess)
  }

  companion object {
    private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_INCREASE
  }
}
