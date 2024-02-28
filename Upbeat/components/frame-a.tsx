import type { NextPage } from "next";
import { useCallback } from "react";
import InstanceF from "./instance-f";
import { useRouter } from "next/router";

const FrameA: NextPage = () => {
  const router = useRouter();

  const onRemainingTime112Click = useCallback(() => {
    const anchor = document.querySelector("[data-scroll-to='frameB']");
    if (anchor) {
      anchor.scrollIntoView({ block: "start", behavior: "smooth" });
    }
  }, []);

  const onChangePlanFrameClick = useCallback(() => {
    router.push("/gameplay1");
  }, [router]);
  const onExitFrameClick = useCallback(() => {
    router.push("/");
  }, [router]);

  return (
    <div className="w-[451px] flex flex-col items-center justify-start pt-1.5 pb-[27px] pr-[19px] pl-[41px] box-border relative gap-[42px_0px] min-w-[451px] max-w-full text-left text-13xl text-black font-inter mq750:min-w-full mq450:gap-[42px_0px] mq1125:flex-1">
      <div
        className="w-full h-full absolute !m-[0] top-[0px] right-[0px] bottom-[0px] left-[0px] rounded-45xl bg-khaki"
        data-scroll-to="frameB"
      />
      <div className="self-stretch flex flex-row items-start justify-start pt-0 pb-[22px] pr-0.5 pl-0 box-border max-w-full text-45xl font-krona-one">
        <div className="flex-1 flex flex-col items-start justify-start gap-[14px_0px] max-w-full">
          <h1 className="m-0 relative text-inherit font-normal font-inherit inline-block max-w-full z-[1] mq450:text-19xl mq1050:text-32xl">
            TURN : 2
          </h1>
          <div className="self-stretch flex flex-row items-start justify-start py-0 pr-0 pl-[13px] box-border max-w-full text-5xl">
            <div
              className="h-[55px] flex-1 relative whitespace-pre-wrap inline-block max-w-full cursor-pointer z-[1] mq450:text-lgi"
              onClick={onRemainingTime112Click}
            >
              Remaining time 1:12
            </div>
          </div>
        </div>
      </div>
      <div className="self-stretch flex flex-row items-start justify-start py-0 px-0.5 box-border max-w-full text-white">
        <div className="w-[335px] flex flex-col items-start justify-start gap-[38px_0px] max-w-full mq450:gap-[38px_0px]">
          <InstanceF player1="You " coin="coin: 2,623" />
          <div className="self-stretch flex flex-col items-start justify-start gap-[11px_0px] max-w-full text-black">
            <InstanceF
              player1="Player 2"
              coin="coin:3,679"
              propRight="-17px"
              propBackgroundColor="#fff"
              propBorder="1px solid #000"
              propColor="#000"
            />
            <div className="self-stretch rounded-4xs bg-gainsboro flex flex-col items-start justify-start pt-1.5 pb-[18px] pr-[26px] pl-[9px] box-border gap-[2px_0px] max-w-full z-[1]">
              <div className="w-[335px] h-56 relative rounded-4xs bg-gainsboro hidden max-w-full" />
              <div className="w-[241px] flex flex-row items-start justify-start py-0 px-0.5 box-border">
                <div className="flex-1 relative z-[2] mq450:text-lgi mq1050:text-7xl">
                  Current Plan:
                </div>
              </div>
              <div className="self-stretch rounded-[2.91px] bg-white overflow-hidden flex flex-row items-center justify-start pt-1 px-1 pb-0 z-[2] text-8xs-7">
                <div className="h-[156px] flex flex-col items-start justify-start pt-0 px-0 pb-0 box-border">
                  <div className="mb-[-36px] relative">
                    <p className="m-0">
                      t = t + 1  # keeping track of the turn number
                    </p>
                    <p className="m-0">m = 0  # number of random moves</p>
                    <p className="m-0">{`while (deposit) { # still our region`}</p>
                    <p className="m-0">  if (deposit - 100)</p>
                    <p className="m-0">
                        then collect (deposit / 4)  # collect 1/4 of available
                      deposit
                    </p>
                    <p className="m-0">
                        else if (budget - 25) then invest 25
                    </p>
                    <p className="m-0">{`  else {}`}</p>
                    <p className="m-0">{`  if (budget - 100) then {} else done  # too poor to do anything else`}</p>
                    <p className="m-0">  opponentLoc = opponent</p>
                    <p className="m-0">  if (opponentLoc / 10 - 1)</p>
                    <p className="m-0">  then  # opponent afar</p>
                    <p className="m-0">
                          if (opponentLoc % 10 - 5) then move downleft
                    </p>
                    <p className="m-0">
                          else if (opponentLoc % 10 - 4) then move down
                    </p>
                    <p className="m-0">
                          else if (opponentLoc % 10 - 3) then move downright
                    </p>
                    <p className="m-0">
                          else if (opponentLoc % 10 - 2) then move right
                    </p>
                    <p className="m-0">
                          else if (opponentLoc % 10 - 1) then move upright
                    </p>
                    <p className="m-0">    else move up</p>
                    <p className="m-0">  else if (opponentLoc)</p>
                    <p className="m-0">
                        then  # opponent adjacent to city crew
                    </p>
                    <p className="m-0">{`    if (opponentLoc % 10 - 5) then {`}</p>
                    <p className="m-0">
                            cost = 10 ^ (nearby upleft % 100 + 1)
                    </p>
                    <p className="m-0">{`      if (budget - cost) then shoot upleft cost else {}`}</p>
                    <p className="m-0">{`    }`}</p>
                    <p className="m-0">{`    else if (opponentLoc % 10 - 4) then {`}</p>
                    <p className="m-0">
                            cost = 10 ^ (nearby downleft % 100 + 1)
                    </p>
                    <p className="m-0">{`      if (budget - cost) then shoot downleft cost else {}`}</p>
                    <p className="m-0">{`    }`}</p>
                    <p className="m-0">{`    else if (opponentLoc % 10 - 3) then {`}</p>
                    <p className="m-0">
                            cost = 10 ^ (nearby down % 100 + 1)
                    </p>
                    <p className="m-0">{`      if (budget - cost) then shoot down cost else {}`}</p>
                    <p className="m-0">{`    }`}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="self-stretch flex flex-row items-start justify-start py-0 pr-0 pl-[19px] box-border max-w-full">
        <div className="flex-1 flex flex-row items-center justify-start gap-[0px_44px] max-w-full mq450:flex-wrap mq450:gap-[0px_44px]">
          <div className="flex-1 flex flex-col items-start justify-start gap-[24px_0px] min-w-[161px]">
            <div className="self-stretch rounded-8xl bg-gainsboro flex flex-row items-center justify-start py-2 pr-[34px] pl-6 whitespace-nowrap z-[1]">
              <div className="h-[55px] w-[248px] relative rounded-8xl bg-gainsboro hidden" />
              <div
                className="relative z-[2]"
                onClick={onChangePlanFrameClick}
                style={{ cursor: "pointer" }}
              >
                Change plan
              </div>
            </div>
            <div className="rounded-8xl bg-gainsboro flex flex-row items-center justify-center py-2 pr-[57px] pl-14 whitespace-nowrap z-[1]">
              <div className="h-[55px] w-[248px] relative rounded-8xl bg-gainsboro hidden" />
              <div className="relative z-[2]">End Turn</div>
            </div>
          </div>
          <div className="flex flex-col items-start justify-start pt-0 px-0 pb-[17px] text-white">
            <div
              className="rounded-xl bg-red overflow-hidden flex flex-row items-center justify-center pt-[15px] px-2.5 pb-[11px] cursor-pointer z-[1]"
              onClick={onExitFrameClick}
            >
              <div className="h-[63px] w-[78px] relative rounded-xl bg-red hidden" />
              <i className="relative font-extrabold z-[1] mq450:text-lgi mq1050:text-7xl">
                Exit
              </i>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default FrameA;
