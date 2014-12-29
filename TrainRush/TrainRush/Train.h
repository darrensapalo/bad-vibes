#pragma once

#include <DNDAnimationStoryboard.h>

class Train : public DNDEntity
{
private:

    bool    direction; // true == up to down, false == down to up

public:

    Train           (const wstring& name        ,
                     bool           direction   );

    void    Setup   (void                       ) override final;
};
