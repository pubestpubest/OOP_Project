import React from "react";
import Map from "../components/map";
// import Region from "./components/region";

const TableMap: React.FC<{ r: number; c: number }> = ({ r, c }) => {
  return (
    <div>
      <div>
        <Map r={r} c={c} />
      </div>
    </div>
  );
};

export default TableMap;
