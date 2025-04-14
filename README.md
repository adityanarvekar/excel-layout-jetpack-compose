# StickyMatrix - Excel-style Layout for Jetpack Compose

`StickyMatrix` is a powerful and customizable composable for creating Excel-style table layouts in Jetpack Compose. It allows you to display tabular data with sticky row and column headers, making it ideal for data-heavy applications such as stock market dashboards, financial reports, or any grid-based UI.

With `StickyMatrix`, you can easily create visually appealing and interactive tables with fully customizable styles and behaviors.

---

## ✨ Features

- **Sticky Headers**: Both the top row (column headers) and the first column (row headers) remain fixed while scrolling.
- **Customizable Styles**: Modify cell dimensions, background colors, text alignment, and text styles for the static cell, row headers, column headers, and data cells.
- **Interactive Cells**: Add long-press callbacks to handle user interactions with individual cells.
- **Divider Colors**: Customize horizontal and vertical divider colors for a polished look.
- **Dynamic Data Binding**: Easily bind your data to display in a tabular format.

---

## 📊 Example Use Case: Stock Market Dashboard

### Table Layout Example

| Symbol | Price   | Change  | Volume |
|--------|---------|---------|--------|
| AAPL   | 178.67  | +0.43%  | 20.1M  |
| GOOG   | 135.29  | -0.15%  | 15.7M  |
| AMZN   | 103.25  | +1.03%  | 13.3M  |
| MSFT   | 310.22  | +0.80%  | 17.6M  |
| TSLA   | 240.78  | -2.14%  | 23.4M  |



In this example:
- The **top row** contains column headers like `Price`, `Change`, and `Volume`.
- The **first column** contains stock tickers such as `AAPL`, `GOOG`, etc.
- The **data cells** contain the corresponding values for each stock under each column header.

---

## 🚀 How to Use StickyMatrix

### Step 0: Add the dependencies in your Gradle files
```
repositories {
	mavenCentral()
	maven { url = uri("https://jitpack.io") }
}

implementation("com.github.adityanarvekar:excel-style-layout-jetpack-compose-android:1.0")
```



### Step 1: Define Your Data Model
```
data class StockInfo(
    val symbol: String,
    val price: String,
    val change: String,
    val volume: String
)
```
### Step 2: Define Column Headers
`val columnHeaders = listOf("Price", "Change", "Volume")`

### Step 3: Define Row Headers
`val rowHeaders = listOf("AAPL", "GOOG", "AMZN", "MSFT", "TSLA")`

### Step 4: Prepare Data
```
val stockData = listOf(
     StockInfo("AAPL", "178.67", "+0.43%", "20.1M"),
     StockInfo("GOOG", "135.29", "-0.15%", "15.7M"),
     StockInfo("AMZN", "103.25", "+1.03%", "13.3M"),
     StockInfo("MSFT", "310.22", "+0.80%", "17.6M"),
     StockInfo("TSLA", "240.78", "-2.14%", "23.4M")
)

val data: List<List<String>> = stockData.map {
     listOf(it.price, it.change, it.volume)
}
```

### Step 5: Use the Composable
```
@Composable
fun PreviewStockStickyMatrix() {
val columnHeaders = listOf("Price", "Change", "Volume")
val rowHeaders = listOf("AAPL", "GOOG", "AMZN", "MSFT", "TSLA")
val stockData = listOf(
    StockInfo("AAPL", "178.67", "+0.43%", "20.1M"),
    StockInfo("GOOG", "135.29", "-0.15%", "15.7M"),
    StockInfo("AMZN", "103.25", "+1.03%", "13.3M"),
    StockInfo("MSFT", "310.22", "+0.80%", "17.6M"),
    StockInfo("TSLA", "240.78", "-2.14%", "23.4M")
)

val data = stockData.map { listOf(it.price, it.change, it.volume) }

StickyMatrix(
    columnHeaders = columnHeaders,
    rowHeaders = rowHeaders,
    data = data,
    modifier = Modifier.fillMaxSize()
)
}
```

---

## 🎨 Customization Options

The `StickyMatrix` composable provides several parameters to customize its appearance and behavior:

| Parameter                    | Description                                                                                  |
|------------------------------|----------------------------------------------------------------------------------------------|
| `columnHeaders`              | List of strings representing the top column headers (sticky).                               |
| `rowHeaders`                 | List of strings representing the first row headers (sticky).                                |
| `data`                       | A 2D list of strings representing the grid content (data cells).                            |
| `modifier`                   | Modifier for styling and positioning the matrix layout in your UI hierarchy.                |
| `defaultCellWidth`           | Width of each cell in the grid (default size).                                              |
| `defaultCellHeight`          | Height of each cell in the grid (default size).                                             |
| `defaultCellBackground`      | Background color of each cell in the grid layout (default color).                           |
| `staticCellContentAlignment` | Alignment of content inside the static cell (top-left corner).                              |
| `staticCellTextStyle`        | Text style for the static cell (e.g., font size, weight, color).                            |
| `staticCellText`             | Text content displayed in the static cell (e.g., a label or icon).                          |
| `columnHeaderHeight`         | Height of the column header row (sticky).                                                   |
| `columnHeaderBackground`     | Background color of the column header row (sticky).                                         |
| `columnHeaderContentAlignment` | Alignment of text inside each column header cell (e.g., center-aligned).                  |
| `rowHeaderBackground`        | Background color of the row header column (sticky).                                         |
| `rowHeaderContentAlignment`  | Alignment of text inside each row header cell (e.g., center-aligned).                       |
| `dataBoxColor`               | Background color of data cells in the grid layout (non-sticky).                             |
| `dataBoxContentAlignment`    | Alignment of text inside each data cell (e.g., left-aligned or right-aligned).              |
| `horizontalCellDividerColor` | Color of horizontal dividers between rows in the grid layout (optional styling option).     |
| `verticalCellDividerColor`   | Color of vertical dividers between columns in the grid layout (optional styling option).     |

---

## 📸 Screenshots & Demo

### GIFs

| ![Screen-recording-1](https://github.com/user-attachments/assets/2be9b171-34af-43a2-85cd-142dea7bfa60) | ![Screen-recording-2](https://github.com/user-attachments/assets/7e16408a-6869-4843-ad91-333010c0b5ce) |
|--------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------|