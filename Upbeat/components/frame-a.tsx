"use client";
import type { NextPage } from "next";
import { useCallback } from "react";
import InstanceF from "./instance-f";
import { useRouter } from "next/router";

import { useState, useEffect } from "react";
import PlanBlock from "./planBlock";

const FrameA: NextPage = () => {
  const router = useRouter();

  const [timeLeft, setTimeLeft] = useState(180); // Initial time (in seconds)

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

  const turnRound = 5;

  // Improved countdown logic using useEffect
  useEffect(() => {
    const intervalId = setInterval(() => {
      setTimeLeft((prevTime) => Math.max(0, prevTime - 1)); // Decrement time and prevent negative values
    }, 1000);

    // Cleanup function to clear the interval on unmount
    return () => clearInterval(intervalId);
  }, []);
  let submitClicked = 1; //0=false ,1 = true
  const timeDisplay = `${timeLeft.toString().padStart(2, "0")}s`; // Formatted time string

  return (
    <div>
      <div className="w-[400px] flex flex-col items-center justify-start mt-5"></div>
      <div className=" flex justify-between items-center  bg-cyan-950 rounded-md border-solid">
        <h1 className=" text-white font-sans mx-10 border-solid ">
          TURN : {turnRound}
        </h1>
        <div className="mx-2">
          <div
            className=" flex justify-between items-center bg-white my-2 mx-3 p-4  rounded-md border-solid font-sans "
            onClick={onRemainingTime112Click}
          >
            Remaining time : {submitClicked == 0 ? timeDisplay : timeLeft}
          </div>
        </div>
      </div>

      <div className="mt-4 mx-2">
        <PlanBlock />
      </div>
    </div>
  );
};

export default FrameA;
