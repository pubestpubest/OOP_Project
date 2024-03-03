export default function HexGrid({ rows, columns }) {
  return (
    <div>
      {Array(rows)
        .fill(null)
        .map((_, rowIndex) => (
          <div
            key={rowIndex}
            class={
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
                  class={
                    colIndex % 2 === 0
                      ? "hex -ml-[10px] bg-green-300 hover:bg-green-500"
                      : "hex -mt-[65px] -ml-[10px] bg-green-300 hover:bg-green-500"
                  }
                >
                  <p class="h-full flex justify-center items-center ">
                    {rowIndex + 1},{colIndex + 1}
                  </p>
                </div>
              ))}
          </div>
        ))}
    </div>
  );
}
