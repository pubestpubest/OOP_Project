import type { NextPage } from "next";
import Buttons from "../components/buttons";

const Home: NextPage = () => {
  return (
    <div className="w-full relative bg-cadetblue overflow-hidden flex flex-col items-start justify-start tracking-[normal]">
      <main className="self-stretch flex flex-row items-start justify-center py-9 px-0 box-border bg-[url('/hero_loaning_to_family.webp')] bg-cover bg-no-repeat bg-[top] min-h-[1180px] max-w-full">
        <img
          style={{ backdropFilter: "blur(50000px)" }}
          className="relative object-cover hidden max-w-full"
          alt=""
          src="/hero_loaning_to_family.webp"
        />

        <section
          className="h-[154px]  relative text-109xl flex italic font-extrabold font-inter text-white text-center items-center justify-center "
          style={{
            textShadow: "2px 2px 4px rgba(3, 3, 3, 5)", // Shadow for the text
            backgroundSize: "cover", // Ensure the background image covers the entire container
          }}
        >
          UPBEAT
        </section>
      </main>
      <Buttons />
    </div>
  );
};

export default Home;
