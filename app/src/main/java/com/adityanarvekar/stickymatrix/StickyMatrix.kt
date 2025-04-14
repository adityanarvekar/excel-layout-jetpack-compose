package com.adityanarvekar.stickymatrix

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @param columnHeaders List of top column headers (Sticky).
 * @param rowHeaders List of first row headers (Sticky).
 * @param data 2D list of strings representing the grid content.
 * @param modifier Modifier for styling and positioning.
 * @param defaultCellWidth Width of each cell in the grid.
 * @param defaultCellHeight Height of each cell in the grid.
 * @param defaultCellBackground Background color of each cell.
 * @param staticCellContentAlignment Alignment of the static cell content.
 * @param staticCellTextStyle Style for the static cell text.
 * @param staticCellText Text content for the static cell.
 * @param columnHeaderHeight Height of column headers.
 * @param columnHeaderBackground Background color of column headers.
 * @param columnHeaderContentAlignment Alignment of column header text.
 * @param columnHeaderTextStyle Style for column header text.
 * @param rowHeaderBackground Background color of row headers.
 * @param rowHeaderContentAlignment Alignment of row header text.
 * @param rowHeaderTextStyle Style for row header text.
 * @param dataBoxColor Background color of data boxes.
 * @param dataBoxContentAlignment Alignment of data box text.
 * @param dataTextStyle Style for data box text.
 * @param horizontalCellDividerColor Color of horizontal cell dividers.
 * @param verticalCellDividerColor Color of vertical cell dividers.
 * @param columnHeaderDividerColor Color of column header dividers.
 * @param onCellLongPress Callback for handling long press on a cell. Returns the Data Row Header.
 * You can use it to fire an event for example an api call or open a dialog box.
 *
 * Example: Display stock data in your StickyMatrix component.
 *
 * **Table Layout Example**
 *
 * ```
 * | Symbol | Price   | Change   | Volume  |
 * -----------------------------------------
 * | AAPL   | 178.67  | +0.43%   | 20.1M   |
 * | GOOG   | 135.29  | -0.15%   | 15.7M   |
 * | AMZN   | 103.25  | +1.03%   | 13.3M   |
 * | MSFT   | 310.22  | +0.80%   | 17.6M   |
 * | TSLA   | 240.78  | -2.14%   | 23.4M   |
 * ```
 *
 * The StickyMatrix layout is structured as follows:
 * - **Top row**: Stock info fields (e.g., Symbol, Price, Change, Volume, etc.)
 * - **First column**: Stock names or tickers (e.g., AAPL, GOOG, etc.)
 * - **Data**: Actual values for each stock under each column header.
 *
 * Steps to implement:
 *
 * 1. **Define Your Data Model**
 *    Create a `StockInfo` data class that represents your table structure:
 *    ```
 *    data class StockInfo(
 *        val symbol: String,
 *        val price: String,
 *        val change: String,
 *        val volume: String
 *    )
 *    ```
 *
 * 2. **Define Column Headers**
 *    These are the field labels displayed at the top of the matrix:
 *    ```
 *    val columnHeaders = listOf("Price", "Change", "Volume")
 *    ```
 *
 * 3. **Define Row Headers**
 *    Each row represents a stock ticker or symbol:
 *    ```
 *    val rowHeaders = listOf("AAPL", "GOOG", "AMZN", "MSFT", "TSLA")
 *    ```
 *
 * 4. **Prepare Data**
 *    Convert your list of `StockInfo` objects into a `List<List<String>>` format that matches the StickyMatrix requirements:
 *    ```
 *    val stockData = listOf(
 *        StockInfo("AAPL", "178.67", "+0.43%", "20.1M"),
 *        StockInfo("GOOG", "135.29", "-0.15%", "15.7M"),
 *        StockInfo("AMZN", "103.25", "+1.03%", "13.3M"),
 *        StockInfo("MSFT", "310.22", "+0.80%", "17.6M"),
 *        StockInfo("TSLA", "240.78", "-2.14%", "23.4M")
 *    )
 *
 *    val data: List<List<String>> = stockData.map {
 *        listOf(it.price, it.change, it.volume)
 *    }
 *    ```
 *
 * 5. **Use the Custom Composable**
 *    Pass the prepared headers and data to your `StickyMatrix` composable function:
 *
 *    ```
 *    @Composable
 *    fun PreviewStockStickyMatrix() {
 *        val columnHeaders = listOf("Price", "Change", "Volume")
 *        val rowHeaders = listOf("AAPL", "GOOG", "AMZN", "MSFT", "TSLA")
 *
 *        val stockData = listOf(
 *            StockInfo("AAPL", "178.67", "+0.43%", "20.1M"),
 *            StockInfo("GOOG", "135.29", "-0.15%", "15.7M"),
 *            StockInfo("AMZN", "103.25", "+1.03%", "13.3M"),
 *            StockInfo("MSFT", "310.22", "+0.80%", "17.6M"),
 *            StockInfo("TSLA", "240.78", "-2.14%", "23.4M")
 *        )
 *
 *        val data = stockData.map { listOf(it.price, it.change, it.volume) }
 *
 *        StickyMatrix(
 *            columnHeaders = columnHeaders,
 *            rowHeaders = rowHeaders,
 *            data = data,
 *            modifier = Modifier.fillMaxSize()
 *        )
 *    }
 *    ```
 */

@Composable
fun StickyMatrix(
    columnHeaders: List<String>,
    rowHeaders: List<String>,
    data: List<List<String>>,
    modifier: Modifier = Modifier,
    defaultCellWidth: Dp,
    defaultCellHeight: Dp,
    defaultCellBackground: Color = Color.White,
    staticCellContentAlignment: Alignment = Alignment.Center,
    staticCellTextStyle: () -> TextStyle = {
        TextStyle.Default.copy(color = Color.Black, fontSize = 14.sp)
    },
    staticCellText: String = "",
    columnHeaderHeight: Dp = 50.dp,
    columnHeaderBackground: Color = Color.LightGray,
    columnHeaderContentAlignment: Alignment = Alignment.Center,
    columnHeaderTextStyle: () -> TextStyle = {
        TextStyle.Default.copy(color = Color.White, fontSize = 14.sp)
    },
    rowHeaderBackground: Color = Color.LightGray,
    rowHeaderContentAlignment: Alignment = Alignment.Center,
    rowHeaderTextStyle: () -> TextStyle = {
        TextStyle.Default.copy(color = Color.White, fontSize = 14.sp)
    },
    dataBoxColor: Color = Color.White,
    dataBoxContentAlignment: Alignment = Alignment.Center,
    dataTextStyle: () -> TextStyle = {
        TextStyle.Default.copy(color = Color.Black, fontSize = 14.sp)
    },
    horizontalCellDividerColor: Color? = null,
    verticalCellDividerColor: Color? = null,
    columnHeaderDividerColor: Color? = null,
    onCellLongPress: ((List<String>) -> Unit)? = null,
) {
    val horizontalScrollState = rememberScrollState()
    val verticalScrollState = rememberLazyListState()

    Column(modifier) {
        // Top Row (Static Top Left + Scrollable Headers)
        Row(modifier = Modifier.fillMaxWidth()) {
            // Top-Left Static Cell
            Box(
                modifier = Modifier
                    .width(defaultCellWidth)
                    .height(defaultCellHeight)
                    .background(defaultCellBackground),
                contentAlignment = staticCellContentAlignment,
            ) {
                Text(text = staticCellText, style = staticCellTextStyle())
            }

            // Scrollable Column Headers
            Row(
                modifier = Modifier
                    .horizontalScroll(horizontalScrollState),
            ) {
                columnHeaders.forEach { header ->
                    Box(
                        modifier = Modifier
                            .width(defaultCellWidth)
                            .height(columnHeaderHeight)
                            .background(columnHeaderBackground)
                            .border(
                                width = if (columnHeaderDividerColor != null) 0.5.dp else 0.dp,
                                color = columnHeaderDividerColor ?: Color.Transparent,
                                shape = RectangleShape // Ensures the border is applied to the rectangle
                            ),
                        contentAlignment = columnHeaderContentAlignment,
                    ) {
                        Text(text = header, style = columnHeaderTextStyle())
                    }
                }
            }
        }

        // Rows
        LazyColumn(
            state = verticalScrollState,
            modifier = Modifier.fillMaxSize(),
        ) {
            items(rowHeaders.size) { rowIndex ->
                Row {
                    // Sticky First Column
                    Box(
                        modifier = Modifier
                            .width(defaultCellWidth)
                            .height(defaultCellHeight)
                            .background(rowHeaderBackground)
                            .border(
                                width = if (horizontalCellDividerColor != null) 0.5.dp else 0.dp,
                                color = horizontalCellDividerColor ?: Color.Transparent,
                                shape = RectangleShape // Ensures the border is applied to the rectangle
                            ),
                        contentAlignment = rowHeaderContentAlignment,
                    ) {
                        Text(text = rowHeaders[rowIndex], style = rowHeaderTextStyle())
                    }

                    // Scrollable Row Cells
                    Row(
                        modifier = Modifier
                            .horizontalScroll(horizontalScrollState)
                    ) {
                        data[rowIndex].forEach { cell ->
                            Box(
                                modifier = Modifier
                                    .width(defaultCellWidth)
                                    .height(defaultCellHeight)
                                    .background(dataBoxColor)
                                    .border(
                                        width = if (verticalCellDividerColor != null) 0.5.dp else 0.dp,
                                        color = verticalCellDividerColor ?: Color.Transparent,
                                        shape = RectangleShape // Ensures the border is applied to the rectangle
                                    )
                                    .pointerInput(Unit) {
                                        detectTapGestures(onLongPress = {
                                            onCellLongPress?.invoke(data[rowIndex]) // Pass row header as callback
                                        })
                                    },
                                contentAlignment = dataBoxContentAlignment,
                            ) {
                                Text(text = cell, style = dataTextStyle())
                            }
                        }
                    }
                }
            }
        }
    }
}