import type { NextPage } from "next";
import { useCallback } from "react";
import Component from "../components/component";
import FrameComponent from "../components/frame-component";
import JavaCodeInput from "../components/javacodeinput";

const Gameplay1: NextPage = () => {
  const onRemainingTime112Click = useCallback(() => {
    const anchor = document.querySelector("[data-scroll-to='frameB']");
    if (anchor) {
      anchor.scrollIntoView({ block: "start", behavior: "smooth" });
    }
  }, []);

  return (
    <div className="w-full relative bg-gray overflow-hidden flex flex-row items-start justify-start pt-[26px] pb-[33px] pr-[38px] pl-[23px] box-border gap-[0px_19px] tracking-[normal] text-left text-45xl text-black font-krona-one mq1125:flex-wrap">
      <div className="flex-1 flex flex-col items-center justify-start pt-1.5 pb-10 pr-[22px] pl-[18px] box-border relative gap-[22px_0px] max-w-full shrink-0 mq750:pb-5 mq750:box-border mq750:min-w-full mq1050:pt-5 mq1050:pb-[26px] mq1050:box-border">
        <div
          className="w-full h-full absolute !m-[0] top-[0px] right-[0px] bottom-[1px] left-[0px] rounded-45xl bg-khaki"
          data-scroll-to="frameB"
        />
        <div className="self-stretch flex flex-row items-start justify-start py-0 pr-2.5 pl-0 box-border max-w-full">
          <div className="flex-1 flex flex-col items-start justify-start gap-[5px_0px] max-w-full mq1125:flex-1">
            <div className="self-stretch flex flex-row items-start justify-start py-0 pr-0 pl-[23px] box-border max-w-full">
              <div className="flex-1 flex flex-row items-end justify-start gap-[0px_55px] max-w-full mq450:gap-[0px_55px] mq1050:flex-wrap">
                <h1 className="m-0 relative text-inherit font-normal font-inherit inline-block max-w-full z-[1] mq450:text-19xl mq1050:text-32xl">
                  TURN : 2
                </h1>
                <div
                  className="h-[55px] flex-1 relative text-5xl whitespace-pre-wrap inline-block min-w-[244px] max-w-full cursor-pointer z-[1] mq450:text-lgi"
                  onClick={onRemainingTime112Click}
                >
                  Remaining time 1:12
                </div>
              </div>
            </div>
            <div className="flex flex-row items-center justify-start py-0 pr-0.5 pl-0 box-border gap-[0px_9px] max-w-full text-13xl text-maroon font-inter mq750:flex-wrap">
              <Component />
              <img
                className="h-[67.9px] w-[67.9px] relative z-[1]"
                loading="eager"
                alt=""
                src="/group1.svg"
              />
              <div className="relative z-[1] mq450:text-lgi mq1050:text-7xl">
                coin: 2,623
              </div>
            </div>
          </div>
        </div>
        <div className="self-stretch flex flex-row items-start justify-start py-0 pr-0 pl-2.5 box-border max-w-full text-13xl font-inter">
          <div className="flex-1 rounded-17xl bg-gainsboro flex flex-col items-start justify-start pt-2.5 pb-[23px] pr-[23px] pl-3.5 box-border gap-[5px_0px] max-w-full z-[1]">
            <div className="w-[812px] h-[745px] relative rounded-17xl bg-gainsboro hidden max-w-full" />
            <JavaCodeInput topic={"Current plan:"} cols={110} />
          </div>
        </div>
      </div>
      <div className="w-[498px] flex flex-col items-start justify-start pt-1 px-0 pb-0 box-border min-w-[498px] max-w-full shrink-0 mq750:min-w-full mq1125:flex-1">
        <FrameComponent />
      </div>
    </div>
  );
};

export default Gameplay1;
