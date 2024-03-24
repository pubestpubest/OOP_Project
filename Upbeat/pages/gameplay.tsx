import type { NextPage } from "next";
import FrameA from "../components/frame-a";
import HexGrid from "../components/HexGird";
import { PlanBlock } from "../components/planBlock";

const Gameplay: NextPage = () => {
  const rowsize = 20;
  const colsize = 20;
  return (
    <div className="h-screen relative bg-green-100 overflow-hidden flex flex-row items-start justify-start  ">
      <div className="ml-5 mt-5 ">
        <FrameA />
      </div>
      <div className="flex-1 flex flex-col items-start justify-start  box-border min-w-[588px] ">
        <div className="mt-8  mb-10 m">
          <div className="max-h-[85vh] max-w-[60vw] overflow-scroll p-8 relative  mb-5 ml-5">
            <HexGrid rows={rowsize} columns={colsize} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Gameplay;
