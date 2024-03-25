"use client";
import type { NextPage } from "next";
import { useCallback } from "react";
import { useRouter } from "next/router";
// import InstanceF from "./instance-f";
import { useState, useEffect } from "react";
import PlanBlock from "./planBlock";
import Task from "./taskPlayer";

const FrameA: NextPage = () => {
  const router = useRouter();

  const [timeLeft, setTimeLeft] = useState(600); // Initial time (in seconds)

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

  // const turnRound = 0;
  const [turnRound, setTurn] = useState(4);

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
  let Yourname = localStorage.getItem("playerName");
  return (
    <div>
      <div className="w-[400px] flex flex-col items-center justify-start "></div>
      <div className=" flex justify-between items-center  bg-cyan-950 rounded-md border-solid">
        <div className=" text-white font-sans mx-10 border-solid text-13xl ">
          TURN : {turnRound}
        </div>
        <div className="mx-2">
          <div
            className=" flex justify-between items-center bg-white my-2 mx-3 p-4  rounded-md border-solid font-sans "
            onClick={onRemainingTime112Click}
          >
            Remaining time : {submitClicked == 0 ? timeDisplay : timeLeft}{" "}
            second
          </div>
        </div>
      </div>
      <div className="overflow-x-scroll overflow-y-hidden h-[20vh] w-[30vw] flex  items-center gap-x-2 ">
        <Task YesYou={false} name={"Phu"} coin={4000} />
        <Task YesYou={true} name={Yourname} coin={3000} />
        <Task YesYou={false} name={"Palm"} coin={6200} />
        <Task YesYou={false} name={"Danial"} coin={3500} />
      </div>

      <div className="mx-2">
        <PlanBlock />
      </div>
    </div>
  );
};

export default FrameA;
