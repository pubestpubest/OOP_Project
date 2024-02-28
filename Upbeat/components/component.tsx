import type { NextPage } from "next";

const Component: NextPage = () => {
  return (
    <div className="w-[151px] flex flex-row items-center justify-start py-[9px] px-3 box-border relative whitespace-nowrap z-[1] text-left text-13xl text-white font-inter">
      <div className="h-full w-full absolute !m-[0] top-[0px] right-[0px] bottom-[0px] left-[0px] rounded-smi bg-black" />
      <div className="relative z-[1]">You :</div>
    </div>
  );
};

export default Component;
