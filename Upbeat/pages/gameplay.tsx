import type { NextPage } from "next";
import FrameA from "../components/frame-a";
import TableMap from "./tablemap";

const Gameplay: NextPage = () => {
  return (
    <div className="w-full relative bg-gray overflow-hidden flex flex-row items-start justify-start pt-[26px] pb-[33px] pr-[38px] pl-[23px] box-border gap-[0px_24px] tracking-[normal] mq1125:flex-wrap">
      <FrameA />
      <section className="flex-1 flex flex-col items-start justify-start pt-1 px-0 pb-0 box-border min-w-[588px] max-w-full mq750:min-w-full">
        {/* <img
          className="self-stretch h-[942px] relative max-w-full overflow-hidden shrink-0"
          loading="eager"
          alt=""
          src="/frame-1.svg"
        /> */}
        <TableMap r={2} c={2} />
      </section>
    </div>
  );
};

export default Gameplay;
