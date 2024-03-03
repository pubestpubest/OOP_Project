import type { NextPage } from "next";
import { useCallback } from "react";
import { useRouter } from "next/router";
import ContainerForText from "../components/container-for-text";


const Config: NextPage = () => {
  const router = useRouter();

  const onButtonContainerClick = useCallback(() => {
    router.push("/");
  }, [router]);

  const onButtonContainer1Click = useCallback(() => {
    router.push("/gameplay");
  }, [router]);

  return (
    <div className="w-full relative bg-lightcoral overflow-hidden flex flex-col items-center justify-start pt-0 pb-[15px] pr-[30px] pl-7 box-border gap-[20px_0px] tracking-[normal] text-center text-109xl text-white font-inter">
      <h1 className="!m-[0] w-full h-[154px] absolute top-[108px] left-[0px] text-inherit flex italic font-extrabold font-inherit items-center justify-center mq1050:text-32xl mq450:text-13xl">
        CONFIG
      </h1>
      <header className="w-[1382px] h-[212px] overflow-x-auto shrink-0 flex flex-row items-start justify-between gap-[20px] max-w-full text-center text-34xl-3 text-white font-inter">
        <div
          className="self-stretch w-[277px] overflow-hidden shrink-0 flex flex-col items-start justify-start py-[66px] px-[17px] box-border relative cursor-pointer z-[1]"
          onClick={onButtonContainerClick}
        >
          <img
            className="w-full h-[106px] absolute !m-[0] top-[calc(50%_-_53px)] right-[0px] left-[0px] max-w-full overflow-hidden shrink-0"
            loading="eager"
            alt=""
            src="/vector1.svg"
          />
          <i className="w-[224.7px] h-[67.9px] relative flex font-bold items-center justify-center shrink-0 z-[1]">
            HOME
          </i>
        </div>
        <div
          className="self-stretch w-[277px] overflow-hidden shrink-0 flex flex-col items-start justify-start py-[66px] px-[17px] box-border relative cursor-pointer z-[1]"
          onClick={onButtonContainer1Click}
        >
          <img
            className="w-full h-[106px] absolute !m-[0] top-[calc(50%_-_53px)] right-[0px] left-[0px] max-w-full overflow-hidden shrink-0"
            loading="eager"
            alt=""
            src="/vector-11.svg"
          />
          <i className="w-[224.7px] h-[67.9px] relative flex font-bold items-center justify-center shrink-0 z-[1]">
            PLAY
          </i>
        </div>
      </header>
      <main className="self-stretch overflow-hidden flex flex-col items-start justify-start pt-[18px] px-0 pb-0 box-border gap-[13px_0px] max-w-full z-[1]">
        <ContainerForText rectangles="Rows" />
        <ContainerForText rectangles="Columns" propMarginBottom="unset" />
        <ContainerForText rectangles="Start Timer" propMarginBottom="unset" />
        <ContainerForText rectangles="Start Budget" propMarginBottom="unset" />
        <ContainerForText
          rectangles="City Center Deposit"
          propMarginBottom="unset"
        />
        <ContainerForText
                  rectangles="revisions Time"
                  propMarginBottom="unset"
                />

        <ContainerForText rectangles="revision cost" propMarginBottom="unset" />
        <ContainerForText
          rectangles="maximum deposit per region"
          propMarginBottom="unset"
        />
        <ContainerForText
          rectangles="interest rate percentage"
          propMarginBottom="unset"
        />
        <ContainerForText
          rectangles="Number of players"
          propMarginBottom="-1px"
        />
      </main>
    </div>
  );
};

export default Config;
