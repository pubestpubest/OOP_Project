import React from "react"; // Assuming this is used in a React component

export default function HexGrid({ rows, columns }) {
  const colorOwn = [];
  const regionMoney = []; // Initialize deposit with default values (0 or other preferred value)
  const player = ["red", "green", "blue", "orange"]; //color to respresent player

  for (let i = 0; i < rows; i++) {
    regionMoney[i] = [];
    for (let j = 0; j < columns; j++) {
      regionMoney[i][j] = 0; // Or any other default value
    }
  }

  for (let i = 0; i < rows; i++) {
    colorOwn[i] = [];
    for (let j = 0; j < columns; j++) {
      colorOwn[i][j] = "-"; // Or any other default value
    }
  }

  // Function to update deposit with custom colors based on row and column
  const updateCell = (rowIndex, colIndex, color) => {
    if (
      rowIndex >= 0 &&
      rowIndex < rows &&
      colIndex >= 0 &&
      colIndex < columns
    ) {
      colorOwn[rowIndex][colIndex] = color;
    } else {
      console.warn(
        `Invalid coordinates: (${rowIndex}, ${colIndex}). Ignoring update.`
      );
    }
  };
  const resetCell = (rowIndex, colIndex) => {
    if (
      rowIndex >= 0 &&
      rowIndex < rows &&
      colIndex >= 0 &&
      colIndex < columns
    ) {
      colorOwn[rowIndex][colIndex] = "-";
      // regionMoney[rowIndex][colIndex] = 0;
    } else {
      console.warn(
        `Invalid coordinates: (${rowIndex}, ${colIndex}). Ignoring reset.`
      );
    }
  };

  updateCell(1, 2, "green");
  updateCell(1, 0, "green");
  // Example usage: Update color at specific positions

  regionMoney[2][4] = 33331;
  regionMoney[2][1] = 11413;
  regionMoney[2][2] = 141232541;

  return (
    <div>
      {Array(rows)
        .fill(null)
        .map((_, rowIndex) => (
          <div
            key={rowIndex}
            className={
              rowIndex === 0
                ? "flex justify-center items-center w-fit"
                : "flex justify-center items-center mt-[8px] w-fit"
            }
          >
            {Array(columns)
              .fill(null)
              .map((_, colIndex) => (
                <div
                  key={`${rowIndex}-${colIndex}`}
                  className={
                    colIndex % 2 === 0
                      ? "hex -ml-[10px] bg-green-300 hover:bg-green-500 border bv bottom-2 "
                      : "hex -mt-[65px] -ml-[10px] bg-green-300 hover:bg-green-500 border"
                  }
                  style={
                    colorOwn[rowIndex][colIndex] != "-"
                      ? { backgroundColor: colorOwn[rowIndex][colIndex] }
                      : { backgroundColor: "bg - green - 300" }
                  }
                >
                  <p className="flex justify-center items-center ">
                    <div>
                      <div className="flex justify-center items-center text-[10px]">
                        <div className="flex flex-col justify-center items-center ">
                          <div> {regionMoney[rowIndex][colIndex]}</div>
                          <div>{colorOwn[rowIndex][colIndex]}</div>
                          <div className="flex justify-center items-center text-[14px]">
                            {rowIndex + 1},{colIndex + 1}
                          </div>
                        </div>
                      </div>
                    </div>
                  </p>
                </div>
              ))}
          </div>
        ))}
    </div>
  );
}
