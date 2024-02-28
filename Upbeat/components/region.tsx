import React from "react";
import { useState } from "react";

const Region: React.FC = () => {
  const [isTaken, setIsTaken] = useState(false);
  const [color, setColor] = useState("#FFFFFF");

  const handleClick = (
    event: React.MouseEvent<SVGPolygonElement, MouseEvent>
  ) => {
    if (event.ctrlKey) {
      setColor(color !== "#0000FF" ? "#0000FF" : "#FFFFFF");
    } else if (event.altKey) {
      setColor(color !== "#00FF00" ? "#00FF00" : "#FFFFFF");
    } else {
      setColor(color !== "#FF0000" ? "#FF0000" : "#FFFFFF");
    }
  };

  return (
    <div style={{ width: "100%", maxWidth: "500px" }}>
      {" "}
      {/* Adjust the maximum width as needed */}
      <svg
        width="100%"
        height="auto"
        viewBox="0 0 100 100"
        preserveAspectRatio="xMidYMid meet"
      >
        <polygon
          onClick={handleClick}
          style={{
            cursor: "pointer",
            fill: color,
            stroke: "black",
            strokeWidth: 1,
          }}
          points="25,5 75,5 100,50 75,95 25,95 0,50"
        />
      </svg>
    </div>
  );
};

export default Region;
