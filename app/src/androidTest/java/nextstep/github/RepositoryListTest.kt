package nextstep.github

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.github.ui.home.RepositoryList
import nextstep.github.ui.home.RepositoryUiState
import org.junit.Rule
import org.junit.Test

class RepositoryListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 데이터가_비어있는경우_목록이비어있습니다_메시지노출() {
        // given
        composeTestRule.setContent {
            RepositoryList(
                uiState = RepositoryUiState.Success(emptyList()),
                onRetry = { }
            )
        }
        // then
        composeTestRule.onNodeWithText("목록이 비었습니다.")
            .assertExists()
    }

    @Test
    fun 오류가발생하는경우_스낵바노출되고_에러메시지와_재시도_버튼이노출된다() {
        // Given
        val errorMessage = "예상치 못한 오류가 발생했습니다."
        val actionLabel = "재시도"
        val uiState = RepositoryUiState.Error(errorMessage)

        // When
        composeTestRule.setContent {
            RepositoryList(
                uiState = uiState,
                onRetry = { }
            )
        }

        // Then
        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
        composeTestRule.onNodeWithText(actionLabel).assertIsDisplayed()
    }
}