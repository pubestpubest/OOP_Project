import type { NextPage } from "next";
import { useCallback } from "react";
import { useRouter } from "next/router";

const TheRule: NextPage = () => {
  const router = useRouter();

  const onButtonContainerClick = useCallback(() => {
    router.push("/");
  }, [router]);

  const onButtonContainer1Click = useCallback(() => {
    router.push("/gameplay");
  }, [router]);

  return (
    <div className="w-full relative bg-goldenrod overflow-hidden flex flex-col items-center justify-start pt-0 pb-[67px] pr-[30px] pl-7 box-border gap-[33px_0px] tracking-[normal] mq750:gap-[33px_0px]">
      <section className="w-full h-[154px] absolute !m-[0] top-[36px] left-[0px] text-109xl flex italic font-extrabold font-inter text-white text-center items-center justify-center mq750:text-32xl mq450:text-13xl">
        THE RULES
      </section>
      <section className="w-[1382px] h-[212px] overflow-x-auto shrink-0 flex flex-row items-start justify-between gap-[20px] max-w-full text-center text-34xl-3 text-white font-inter">
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
          <i className="w-[224.7px] h-[67.9px] relative flex font-bold items-center justify-center shrink-0 z-[1] mq750:text-24xl mq450:text-13xl">
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
          <i className="w-[224.7px] h-[67.9px] relative flex font-bold items-center justify-center shrink-0 z-[1] mq750:text-24xl mq450:text-13xl">
            PLAY
          </i>
        </div>
      </section>
      <section className="self-stretch flex flex-row items-start justify-start py-0 pr-7 pl-2.5 box-border max-w-full text-left text-13xl text-white font-inter">
        <div className="flex-1 flex flex-col items-start justify-start gap-[45px_0px] max-w-full mq675:gap-[45px_0px]">
          <div className="w-[1312px] flex flex-row flex-wrap items-start justify-start gap-[0px_43px] max-w-full mq675:gap-[0px_43px]">
            <div className="flex-1 flex flex-col items-start justify-start pt-0.5 px-0 pb-0 box-border min-w-[621px] max-w-full mq750:min-w-full">
              <i className="self-stretch h-[342px] relative leading-[121.88%] inline-block font-extrabold shrink-0 mq750:text-7xl mq750:leading-[31px] mq450:text-lgi mq450:leading-[23px]">
                <p className="[margin-block-start:0] [margin-block-end:42px] whitespace-pre-wrap">
                  {" "}
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                  Aenean laoreet, sapien nec placerat ultricies, nisl nisl
                  faucibus quam, nec mollis neque turpis at ipsum. Vivamus quis
                  tellus consectetur, congue quam quis, aliquet massa. Integer
                  posuere a arcu sit amet eleifend. Aliquam fermentum nisi nec
                  nisi semper gravida sed in ante. Suspendisse ut molestie nibh.
                  Proin eget est pulvinar, tincidunt arcu vel, dapibus lectus.
                  Nulla consectetur libero elit, et posuere dui porttitor sed.
                  Nulla sagittis finibus vestibulum.
                </p>
              </i>
            </div>
            <img
              className="h-[324px] w-[313px] relative object-cover"
              loading="eager"
              alt=""
              src="/image-1@2x.png"
            />
          </div>
          <footer className="self-stretch h-[323px] relative text-13xl leading-[121.88%] inline-block italic font-extrabold font-inter text-white whitespace-pre-wrap text-left shrink-0 mq750:text-7xl mq750:leading-[31px] mq450:text-lgi mq450:leading-[23px]">
            {" "}
            Donec at risus eget odio pellentesque interdum. Curabitur gravida
            pharetra odio, nec volutpat urna pulvinar blandit. Curabitur porta
            quis diam vel eleifend. Sed a pharetra est, quis mattis nisl.
            Pellentesque tempus condimentum ex, sed dapibus purus faucibus nec.
            Maecenas id varius augue. Aliquam erat volutpat. Maecenas pulvinar
            ligula sed ipsum elementum, in sagittis ex suscipit. Quisque sed
            lacinia ante. Pellentesque accumsan, erat at iaculis aliquet, nibh
            ex gravida leo, ut congue magna risus ac tortor. Aliquam venenatis
            consectetur quam eu imperdiet. Maecenas scelerisque ex eu tortor
            hendrerit congue. Quisque pharetra ante risus, quis vehicula quam
            cursus at.
          </footer>
        </div>
      </section>
    </div>
  );
};

export default TheRule;
