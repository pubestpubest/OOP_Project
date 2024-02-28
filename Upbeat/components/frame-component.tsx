import type { NextPage } from "next";
import { useCallback } from "react";
import { useRouter } from "next/router";
import TableMap from "../pages/tablemap";

const FrameComponent: NextPage = () => {
  const router = useRouter();

  const onFrameContainerClick = useCallback(() => {
    router.push("/");
  }, [router]);

  return (
    <div className="self-stretch flex flex-col items-end justify-start gap-[101px_0px] max-w-full text-center text-41xl text-black font-inter mq750:gap-[101px_0px]">
      <TableMap r={8} c={11} />
      <div className="self-stretch flex flex-row items-start justify-start py-0 pr-10 pl-[37px] box-border max-w-full">
        <div className="flex-1 overflow-hidden flex flex-col items-start justify-start py-[53px] px-[26px] box-border relative min-h-[171px] max-w-full">
          <img
            className="w-full h-[85.5px] absolute !m-[0] top-[42.8px] right-[0px] left-[0px] max-w-full overflow-hidden shrink-0"
            loading="eager"
            alt=""
            src="/vector2.svg"
          />
          <h2 className="m-0 w-[341.3px] h-[54.8px] relative text-inherit flex italic font-bold font-inherit items-center justify-center shrink-0 max-w-full z-[1] mq450:text-17xl mq1050:text-29xl">
            Confirm
          </h2>
        </div>
      </div>
      <div className="flex flex-row items-start justify-start py-0 px-[13px] text-left text-13xl text-white">
        <div
          className="rounded-xl bg-red overflow-hidden flex flex-row items-center justify-center pt-[15px] px-2.5 pb-[11px] cursor-pointer"
          onClick={onFrameContainerClick}
        >
          <div className="h-[63px] w-[78px] relative rounded-xl bg-red hidden" />
          <i className="relative font-extrabold z-[1] mq450:text-lgi mq1050:text-7xl">
            Exit
          </i>
        </div>
      </div>
    </div>
  );
};

export default FrameComponent;
