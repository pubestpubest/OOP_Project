import React from "react";
import Region from "./region";

const Map: React.FC<{ r: number; c: number }> = ({ r, c }) => {
  const n = r;
  const m = c;
  const Regions = [];
  // const spaces = "\u00A0".repeat(18);
  // const spacebtwrow = "\u00A0".repeat(14);
  // Outer loop for rows
  for (let i = 0; i < n; i++) {
    const row = [];

    // Inner loop for columns
    for (let j = 0; j < m; j++) {
      if (j % 2 == 0) {
        row.push(
          <div>
            <Region key={i * m + j} />
          </div>
        );
      } else {
        row.push(
          <div style={{ margin: "-5% -3% " }}>
            <Region key={i * m + j} />
          </div>
        );
      }
    }
    Regions.push(
      <div
        key={i}
        style={{
          marginTop: -20,
          marginBottom: -20,
          height: "80%",
          display: "flex",
        }}
      >
        {row}
      </div>
    );
  }

  return (
    <div
      style={{
        width: "100%", // Take up full width of the container
        maxWidth: "800px", // Set maximum width for better responsiveness

        margin: "40px  auto", // Center the map horizontally and add top margin
      }}
    >
      {Regions}
    </div>
  );
};

export default Map;
