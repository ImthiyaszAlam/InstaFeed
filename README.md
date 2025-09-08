#  Instagram-Style Feed (Android)

An **Instagram-like feed UI** built with **RecyclerView + ConstraintLayout**, supporting both **images** and **videos** with dynamic aspect ratios (square, portrait, landscape).   

---

## Features

-  **Image Posts**
  - Supports remote URLs and local drawable resources
  - Dynamic aspect ratio (1:1, 16:9, 9:16, etc.)
  - Overlay text captions

-  **Video Posts**
  - Plays videos from local resources
  - Auto-looping & muted playback (Instagram-style)
  - Dynamic aspect ratio handling
  - Overlay text captions

-  **Feed with RecyclerView**
  - Efficient item recycling
  - Separate ViewHolders for Image & Video
  - Smooth scrolling performance

---

##  Tech Stack

- **Language:** Kotlin  
- **UI:** ConstraintLayout, RecyclerView  
- **Media:** VideoView, Glide for image loading  
- **Design:** Aspect ratio scaling with `ConstraintLayout.LayoutParams.dimensionRatio`
