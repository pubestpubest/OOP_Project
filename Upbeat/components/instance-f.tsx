import type { NextPage } from "next";
import { useMemo, type CSSProperties } from "react";

export type InstanceFType = {
  player1?: string;
  coin?: string;

  /** Style props */
  propRight?: CSSProperties["right"];
  propBackgroundColor?: CSSProperties["backgroundColor"];
  propBorder?: CSSProperties["border"];
  propColor?: CSSProperties["color"];
};

const InstanceF: NextPage<InstanceFType> = ({
  player1,
  coin,
  propRight,
  propBackgroundColor,
  propBorder,
  propColor,
}) => {
  const coinAStyle: CSSProperties = useMemo(() => {
    return {
      right: propRight,
      backgroundColor: propBackgroundColor,
      border: propBorder,
    };
  }, [propRight, propBackgroundColor, propBorder]);

  const player1Style: CSSProperties = useMemo(() => {
    return {
      color: propColor,
    };
  }, [propColor]);

  return (
    <div className="w-80 flex flex-row items-start justify-start py-0 px-[11px] box-border text-left text-13xl text-white font-inter">
      <div className="flex-1 overflow-hidden flex flex-col items-start justify-start pt-[9px] px-0.5 pb-px gap-[19px_0px] z-[1]">
        <div className="flex flex-row items-start justify-start py-0 px-3">
          <div className="flex flex-row items-start justify-start relative">
            <div
              className="h-[57px] w-[151px] absolute !m-[0] top-[calc(50%_-_28.5px)] right-[-82px] rounded-smi bg-black"
              style={coinAStyle}
            />
            <div
              className="relative z-[1] mq450:text-lgi mq1050:text-7xl"
              style={player1Style}
            >
              {player1}
            </div>
          </div>
        </div>
        <div className="flex flex-row items-center justify-start gap-[0px_11px] text-maroon mq450:flex-wrap">
          <img
            className="h-[67.9px] w-[67.9px] relative"
            loading="eager"
            alt=""
            src="/group.svg"
          />
          <div className="flex flex-col items-start justify-start pt-0 px-0 pb-1.5">
            <div className="relative mq450:text-lgi mq1050:text-7xl">
              {coin}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default InstanceF;
