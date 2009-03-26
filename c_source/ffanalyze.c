#include "ffanalyze.h"

int main(int argc, char ** argv) {
  AVFormatContext *pFormatCtx;

  char * vid_filename = argv[1];
  char * met_filename = argv[2];

  if (argc != 3) {
    help();
    return -1;
  }

  av_register_all(); //register all codecs with our program

  if (av_open_input_file(&pFormatCtx, vid_filename, NULL, 0, NULL) != 0) {
    perror("couldn't open file");
    return -1; //couldn't open file
  }

  if (av_find_stream_info(pFormatCtx)<0){
    perror("couldn't find stream information");
    return -1; //couldn't find stream information
  }

  getCtxInfo(pFormatCtx);

  return 0;
}

//displays program usage
void help(){
  printf("\nusage:\n\tffanalyze <input file> <metrics file>\n\n");
}

//displays information about the given context
void getCtxInfo(AVFormatContext *pFormatCtx){
  printf("\n");
  
  printf("avcontext info:\n");
  printf("\t duration:\t%d\n", (int) pFormatCtx -> duration);
  printf("\t file size:\t%d\n", (int) pFormatCtx -> file_size);
  printf("\t bitrate:\t%d\n", (int) pFormatCtx -> bit_rate);
    
  printf("\n");
}
