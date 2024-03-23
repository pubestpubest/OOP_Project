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
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    <div className="bg-gradient-to-t from-sky-700 to-slate-100 flex flex-col items-center justify-center  ">
      <div className="flex flex-col items-center justify-center">
        <div className="flex-col items-center justify-center">
          <section className=" flex flex-row items-center justify-center  gap-[45vh] pt-10">
            <button
              onClick={onButtonContainerClick}
              className=" cursor-pointer border-solid border-2 rounded-xl p-5 focus:ring-4 focus:outline-none focus:ring-white font-bold  text-7xl px-6 py-4  transition ease-in-out  transform hover:-translate-y-1 hover:scale-110 m-4"
            >
              &lt;&lt; Home
            </button>

            <div className="text-41xl flex items-center justify-center text-white font-mono font-bold">
              <div className="mt-5 flex border-solid border-2 rounded-xl p-5 bg-gradient-to-r from-blue-700 to-sky-400 shadow-xl ">
                <div className="text-wrap"> Config Game</div>
              </div>
            </div>
            <button
              onClick={onButtonContainer1Click}
              className="cursor-pointer border-solid border-2 rounded-xl p-5 focus:ring-4 focus:outline-none focus:ring-white font-bold  text-7xl px-6 py-4  transition ease-in-out  transform hover:-translate-y-1 hover:scale-110 m-4"
            >
              PLAY &gt;&gt;
            </button>
          </section>
        </div>
        <div className="p-5 flex flex-col items-center justify-center ">
          <div className="p-2 ">
            <ContainerForText rectangles="rows" />
          </div>
          <div className="p-2 ">
            <ContainerForText rectangles="columns" />
          </div>
          <div className="p-2 ">
            <ContainerForText rectangles="startTimer" />
          </div>
          <div className="p-2 ">
            <ContainerForText rectangles="startBudget" />
          </div>
          <div className="p-2 ">
            <ContainerForText rectangles="cityCenterDeposit" />
          </div>
          <div className="p-2 ">
            <ContainerForText rectangles="revisionsTime" />
          </div>
          <div className="p-2 ">
            <ContainerForText rectangles="revisioncost" />
          </div>
          <div className="p-2 ">
            <ContainerForText rectangles="maximumDepositPerRegion" />
          </div>
          <div className="p-2 ">
            <ContainerForText rectangles="interestRatePercentage" />
          </div>
          <div className="p-2 ">
            <ContainerForText rectangles="numberOfPlayers" />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Config;
