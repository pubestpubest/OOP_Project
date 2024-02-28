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
    <section className="self-stretch flex flex-row items-start justify-start py-0 px-0 box-border max-w-full text-left text-45xl text-white font-inter">
      <div
        className="w-[1440px] overflow-hidden shrink-0 flex flex-row flex-wrap items-start justify-start py-0 pr-[106px] pl-[86px] box-border gap-[0px_23px] max-w-[104%] mq750:pl-[43px] mq750:pr-[53px] mq750:box-border mq450:pl-5 mq450:pr-5 mq450:box-border"
        style={configBoxStyle}
      >
        <div className="flex-1 flex flex-col items-start justify-start pt-0 px-0 pb-0 box-border min-w-[667px] max-w-full mq1050:min-w-full">
          <i className="mb-[-1px] self-stretch h-[114px] relative flex font-extrabold items-center shrink-0 mq1050:text-32xl mq450:text-19xl">
            {rectangles}
          </i>
        </div>
        <div className="h-[113px] w-[199px] relative rounded-22xl bg-gainsboro box-border border-[9px] border-solid border-black" />
      </div>
    </section>
  );
};

export default ContainerForText;
