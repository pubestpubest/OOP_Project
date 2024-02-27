"use client";
import React from "react";
import { useState } from "react";

const Region: React.FC = () => {
  const [isTaken, setIsTaken] = useState(false);
  const [color, setColor] = useState("#FFFFFF");

  const handleClick = (
    event: React.MouseEvent<SVGPolygonElement, MouseEvent>
  ) => {
    // Check if Ctrl key is pressed
    if (event.ctrlKey) {
      if (color !== "#0000FF") {
        setIsTaken(false);
        setColor("#0000FF");
      } else {
        setIsTaken(false);
        setColor("#FFFFFF");
      }
    } else if (event.altKey) {
      if (color !== "#00FF00") {
        setIsTaken(false);
        setColor("#00FF00");
      } else {
        setIsTaken(false);
        setColor("#FFFFFF");
      }
    } else {
      // If region is already taken, untake it and change color to white
      if (color !== "#FF0000") {
        setIsTaken(false);
        setColor("#FF0000");
      } else {
        setIsTaken(false);
        setColor("#FFFFFF");
      }
    }
  };

  return (
    <svg width="100" height="100" viewBox="0 0 100 100">
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
  );
};

export default Region;
