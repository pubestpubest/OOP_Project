import type { NextPage } from "next";
import { useMemo, type CSSProperties } from "react";

export type ContainerForTextType = {
  rectangles?: string;
  /** Style props */
  propMarginBottom?: CSSProperties["marginBottom"];
};

const ContainerForText: NextPage<ContainerForTextType> = ({
  rectangles,
  propMarginBottom,
}) => {
  const configBoxStyle: CSSProperties = useMemo(() => {
    return {
      marginBottom: propMarginBottom,
    };
  }, [propMarginBottom]);

  return (
    <section className=" flex-row items-start justify-start  text-45xl text-white font-inter">
      <div
        className="w-[1440px] overflow-hidden shrink-0 flex flex-row flex-wrap items-start justify-start px-9 box-border "
        style={configBoxStyle}
      >
        <div className="flex-1  flex-col items-start justify-start  box-border">
          <i className="relative flex font-bold items-center ">{rectangles}</i>
        </div>
        <div className="h-[113px] w-[199px] relative rounded-22xl bg-gainsboro box-border border-[9px] border-solid border-black" />
      </div>
    </section>
  );
};

export default ContainerForText;
