import React from "react"; // Assuming this is used in a React component

export default function HexGrid({ rows, columns }) {
  const colorOwn = []; //สีในช่อง
  const regionMoney = []; //เงินในช่อง
  const whoseCapital = []; //เมืองหลวงใคร
  const whoseRegion = []; //เมืองใคร
  let Yourname = localStorage.getItem("playerName");
  const playerName = ["Phu", Yourname, "Palm", "Danial", "-"];
  const Color = ["red", "green", "blue", "purple", ""];

  function getRandomInt(max) {
    return Math.floor(Math.random() * max);
  }
  for (let i = 0; i < rows; i++) {
    const randomMoney = getRandomInt(1200);
    regionMoney[i] = [];
    colorOwn[i] = [];
    whoseCapital[i] = [];
    whoseRegion[i] = [];

    for (let j = 0; j < columns; j++) {
      regionMoney[i][j] = randomMoney; // Or any other default value
      colorOwn[i][j] = "-"; // Or any other default value
      whoseCapital[i][j] = "(--)";
      whoseRegion[i][j] = "-";
    }
  }

  // for (let i = 0; i < rows; i++) {
  //   colorOwn[i] = [];
  //   for (let j = 0; j < columns; j++) {
  //     colorOwn[i][j] = "-"; // Or any other default value
  //   }
  // }

  // Function to update deposit with custom colors based on row and column
  const updateCell = (
    rowIndex,
    colIndex,
    color,
    ownerCapital,
    ownerRegion,
    money
  ) => {
    if (
      rowIndex >= 0 &&
      rowIndex < rows &&
      colIndex >= 0 &&
      colIndex < columns
    ) {
      colorOwn[rowIndex][colIndex] = color;
      whoseCapital[rowIndex][colIndex] = ownerCapital;
      whoseRegion[rowIndex][colIndex] = ownerRegion;
      regionMoney[rowIndex][colIndex] = money;
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
      colorOwn[rowIndex][colIndex] = "";
      whoseCapital[rowIndex][colIndex] = "(--)";
      whoseRegion[rowIndex][colIndex] = "-";
      regionMoney[rowIndex][colIndex] = 0;
    } else {
      console.warn(
        `Invalid coordinates: (${rowIndex}, ${colIndex}). Ignoring update.`
      );
    }
  };

  const setAllColors = (color) => {
    for (let i = 0; i < rows + 1; i++) {
      for (let j = 0; j < columns + 1; j++) {
        updateCell(i, j, color, "player", "()", "0"); // Preserve region money
      }
    }
  };

  //for example set Up wait for API
  let i = 0;
  let j = 1;
  let k = 2;
  let l = 3;
  //phu
  updateCell(1, 2, Color[i], playerName[i], playerName[i], "0"); //(row,cols,color,ownCapital,ownRegion,money)
  updateCell(2, 2, Color[i], "(--)", playerName[i], "0"); //(row,cols,color,ownCapital,ownRegion,money)
  updateCell(3, 2, Color[i], "(--)", playerName[i], "0"); //(row,cols,color,ownCapital,ownRegion,money)
  updateCell(3, 1, Color[i], "(--)", playerName[i], "0"); //(row,cols,color,ownCapital,ownRegion,money)
  //fluke
  updateCell(2, 3, Color[j], playerName[j], playerName[j], "0");
  updateCell(2, 4, Color[j], "(--)", playerName[j], "0");
  updateCell(2, 5, Color[j], "(--)", playerName[j], "0");
  updateCell(2, 6, Color[j], "(--)", playerName[j], "0");
  //palm
  updateCell(6, 2, Color[k], playerName[k], playerName[k], "0"); //(row,cols,color,ownCapital,ownRegion,money)
  updateCell(7, 2, Color[k], "(--)", playerName[k], "0"); //(row,cols,color,ownCapital,ownRegion,money)
  updateCell(7, 3, Color[k], "(--)", playerName[k], "0"); //(row,cols,color,ownCapital,ownRegion,money)
  updateCell(6, 1, Color[k], "(--)", playerName[k], "0"); //(row,cols,color,ownCapital,ownRegion,money)
  //Danial
  updateCell(4, 10, Color[l], playerName[l], playerName[l], "0"); //(row,cols,color,ownCapital,ownRegion,money)
  updateCell(5, 11, Color[l], "(--)", playerName[l], "0"); //(row,cols,color,ownCapital,ownRegion,money)
  updateCell(4, 12, Color[l], "(--)", playerName[l], "0"); //(row,cols,color,ownCapital,ownRegion,money)
  updateCell(5, 12, Color[l], "(--)", playerName[l], "0"); //(row,cols,color,ownCapital,ownRegion,money)
  // setAllColors("red");
  // setAllColors("");

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
                      ? "hex -ml-[10px] bg-slate-300 hover:bg-green-500 border bv bottom-2 "
                      : "hex -mt-[65px] -ml-[10px] bg-slate-300  hover:bg-green-500 border"
                  }
                  style={
                    colorOwn[rowIndex][colIndex] != ""
                      ? {
                          backgroundColor: colorOwn[rowIndex][colIndex],
                          // opacity: "60%",
                        }
                      : { backgroundColor: "" }
                  }
                >
                  <div className="flex justify-center items-center ">
                    <div>
                      <div
                        className={
                          colorOwn[rowIndex][colIndex] != ""
                            ? "flex justify-center items-center text-[10px] text-white"
                            : "flex justify-center items-center text-[10px]"
                        }
                      >
                        <div className="flex flex-col justify-center items-center mt-1 ">
                          {/* //เงิน */}
                          <div>{regionMoney[rowIndex][colIndex]}</div>
                          {/* //แสดงสถานะเมืองหลวงของใคร     */}
                          <div>C :{whoseCapital[rowIndex][colIndex]}</div>
                          {/* //เจ้าของ */}
                          <div>
                            {">"}
                            {whoseRegion[rowIndex][colIndex]} {"<"}
                          </div>
                          {/* //ตำแหน่ง Region   */}
                          <div className="flex justify-center items-center text-[14px]">
                            {rowIndex},{colIndex}
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              ))}
          </div>
        ))}
    </div>
  );
}
