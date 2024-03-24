import type { NextPage } from "next";
import { useMemo, type CSSProperties } from "react";
import { useState } from "react";
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
  const [text, setText] = useState(""); // State for the text area value

  const addinputText = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    setText(event.target.value); // Update text area state on change
  };

  return (
    <section className=" flex-row items-start justify-start  text-45xl text-white font-inter">
      <div
        className="w-[1440px] overflow-hidden shrink-0 flex flex-row flex-wrap items-start justify-start px-9 box-border "
        style={configBoxStyle}
      >
        <div className="flex-1  flex-col items-start justify-start  box-border mx-20">
          <i>{rectangles}</i>
        </div>
        {/* <div className="h-[113px] w-[199px] relative rounded-22xl bg-gainsboro box-border border-[9px] border-solid border-black" /> */}
        <textarea
          style={{ resize: "none" }}
          className="rounded-full border-solid  border-2 h-[30px] w-[180px] flex  text-center items-center justify-center mt-7 mx-20"
          placeholder={rectangles}
          onChange={addinputText}
          value={text}
        />
      </div>
      ///
      <div className=" text-7xl">{text}</div>
    </section>
  );
};

export default ContainerForText;


{
  /* <textarea
      style={{ resize: "none" }}
      type="text"
      className="p-1 mx-2  rounded-md border-solid  border-2 h-[90px] w-[350px]  overflow-scroll  "
      placeholder="Enter your code here"
      onChange={inputText}
      value={text}
    /> */
}
