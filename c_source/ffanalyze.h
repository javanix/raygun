#include <libavcodec/avcodec.h>
#include <libavformat/avformat.h>
#include <libavformat/avio.h>

#include <stdio.h>

//displays program usage
void help();

//displays information about the given stream
void getCtxInfo(AVFormatContext *pFormatCtx);
