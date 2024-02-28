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
        <section className="self-stretch overflow-hidden flex flex-row flex-wrap items-start justify-start pt-0 pb-[22px] pr-12 pl-[86px] box-border gap-[0px_23px] max-w-full text-left text-45xl text-white font-inter lg:pl-[43px] lg:pr-6 lg:box-border mq750:pl-[21px] mq750:box-border">
          <i className="h-[114px] flex-1 relative flex font-extrabold items-center min-w-[370px] max-w-full mq750:min-w-full mq1050:text-32xl mq450:text-19xl">
            revisions Time
          </i>
          <div className="h-[113px] w-[199px] relative rounded-22xl bg-gainsboro box-border border-[9px] border-solid border-black" />
        </section>
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
