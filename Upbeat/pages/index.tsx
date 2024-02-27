import type { NextPage } from "next";
import Buttons from "../components/buttons";

const Home: NextPage = () => {
  return (
    <div className="w-full relative bg-cadetblue overflow-hidden flex flex-col items-start justify-start tracking-[normal]">
      <main className="self-stretch flex flex-row items-start justify-center py-9 px-0 box-border bg-[url('/asdqeremovebgpreview-1@2x.png')] bg-cover bg-no-repeat bg-[top] min-h-[866px] max-w-full ">
        <img
          style={{ backdropFilter: "blur(50000px)" }}
          className="h-[1001px] w-[1502px] relative object-cover hidden max-w-full"
          alt=""
          src="/asdqeremovebgpreview-1@2x.png"
        />

        <section className="h-[154px]  relative text-109xl flex italic font-extrabold font-inter text-white text-center items-center justify-center ">
          UPBEAT
        </section>
      </main>
      <Buttons />
    </div>
  );
};

export default Home;
