import React from "react";
import Region from "./region";

const Map: React.FC<{ r: number; c: number }> = ({ r, c }) => {
  const n = r;
  const m = c;
  const Regions = [];
  const spaces = "\u00A0".repeat(18);
  const spacebtwrow = "\u00A0".repeat(14);
  // Outer loop for rows
  for (let i = 0; i < n; i++) {
    const row = [];
    // Inner loop for columns
    for (let j = 0; j < m; j++) {
      row.push(<Region key={i * m + j} />);
      row.push(spacebtwrow);
    }
    Regions.push(
      i % 2 ? (
        <div
          key={i}
          style={{
            display: "flex",
          }}
        >
          {row}
        </div>
      ) : (
        <div
          key={i}
          style={{
            display: "flex",
            marginBottom: "-50px",
            marginTop: "-50px",
          }}
        >
          {spaces}
          {row}
        </div>
      )
    );
  }

  return <div>{Regions}</div>;
};

export default Map;
